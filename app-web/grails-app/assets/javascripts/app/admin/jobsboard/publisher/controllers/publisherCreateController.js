//= wrapped

angular
    .module("app.admin.jobsboard.publisher")
    .controller("PublisherCreateController", PublisherCreateController);

function PublisherCreateController(Publisher, $state) {

    var vm = this;
    
    vm.publisher = new Publisher();
    
    vm.savePublisher = function() {
        vm.errors = undefined;
        vm.publisher.$save({}, function() {
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
