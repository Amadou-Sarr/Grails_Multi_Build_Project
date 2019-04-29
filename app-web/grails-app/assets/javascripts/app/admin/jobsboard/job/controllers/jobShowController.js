//= wrapped

angular
    .module("app.admin.jobsboard.job")
    .controller("JobShowController", JobShowController);

function JobShowController(Job, $stateParams, $state) {
    var vm = this;

    Job.get({id: $stateParams.id}, function(data) {
        vm.job = new Job(data);
    }, function() {
        $state.go('job.list');
    });

    vm.delete = function() {
        vm.job.$delete(function() {
            $state.go('job.list');
        }, function() {
            //on error
        });
    };

}
