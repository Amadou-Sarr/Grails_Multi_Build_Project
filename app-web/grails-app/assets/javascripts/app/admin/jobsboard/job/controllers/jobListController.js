//= wrapped

angular
    .module("app.admin.jobsboard.job")
    .controller("JobListController", JobListController);

function JobListController(Job) {
    var vm = this;

    var max = 10, offset = 0;

    Job.list({max: max, offset: offset}, function(data) {
        vm.jobList = data;
    });

    vm.publisherList = Publisher.list();
}
