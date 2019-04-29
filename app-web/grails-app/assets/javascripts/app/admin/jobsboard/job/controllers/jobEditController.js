//= wrapped

angular
    .module("app.admin.jobsboard.job")
    .controller("JobEditController", JobEditController);

function JobEditController(Job, $stateParams, $state, Type, Tag, Publisher) {
    var vm = this;

    vm.typeList = Type.list();
    vm.tagList = Tag.list();
    vm.publisherList = Publisher.list();

    Job.get({id: $stateParams.id}, function(data) {
        vm.job = new Job(data);
    }, function() {
        vm.errors = [{message: "Could not retrieve job with ID " + $stateParams.id}];
    });

    vm.updateJob = function() {
        vm.errors = undefined;
        vm.job.$update(function() {
            $state.go('job.show', {id: vm.job.id});
        }, function(response) {
            var data = response.data;
            if (data.hasOwnProperty('message')) {
                vm.errors = [data];
            } else {
                vm.errors = data._embedded.errors;
            }
        });
    };
}
