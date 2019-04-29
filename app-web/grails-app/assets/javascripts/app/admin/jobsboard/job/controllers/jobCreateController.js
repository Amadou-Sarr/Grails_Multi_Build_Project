//= wrapped

angular
    .module("app.admin.jobsboard.job")
    .controller("JobCreateController", JobCreateController);

function JobCreateController(Job, $state, Type, Tag, Publisher) {

    var vm = this;
    vm.typeList = Type.list();
    vm.tagList = Tag.list();
    vm.publisherList = Publisher.list();
    vm.job = new Job();
    
    vm.saveJob = function() {
        vm.errors = undefined;
        vm.job.$save({}, function() {
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
