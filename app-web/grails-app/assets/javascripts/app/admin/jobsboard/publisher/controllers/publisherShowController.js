//= wrapped

angular
    .module("app.admin.jobsboard.publisher")
    .controller("PublisherShowController", PublisherShowController);

function PublisherShowController(Publisher, $stateParams, $state) {
    var vm = this;

    Publisher.get({id: $stateParams.id}, function(data) {
        vm.publisher = new Publisher(data);
    }, function() {
        $state.go('publisher.list');
    });

    vm.delete = function() {
        vm.publisher.$delete(function() {
            $state.go('publisher.list');
        }, function() {
            //on error
        });
    };

}
