//= wrapped

angular
    .module("app.admin.jobsboard.publisher")
    .controller("PublisherEditController", PublisherEditController);

function PublisherEditController(Publisher, $stateParams, $state) {
    var vm = this;

    

    Publisher.get({id: $stateParams.id}, function(data) {
        vm.publisher = new Publisher(data);
    }, function() {
        vm.errors = [{message: "Could not retrieve publisher with ID " + $stateParams.id}];
    });

    vm.updatePublisher = function() {
        vm.errors = undefined;
        vm.publisher.$update(function() {
            $state.go('publisher.show', {id: vm.publisher.id});
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
