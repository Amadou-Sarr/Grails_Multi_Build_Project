//= wrapped

angular
    .module("app.admin.jobsboard.publisher")
    .controller("PublisherListController", PublisherListController);

function PublisherListController(Publisher) {
    var vm = this;

    var max = 10, offset = 0;

    Publisher.list({max: max, offset: offset}, function(data) {
        vm.publisherList = data;
    });
}
