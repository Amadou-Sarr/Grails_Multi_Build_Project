//= wrapped

angular
    .module("app.admin.jobsboard.type")
    .controller("TypeCreateController", TypeCreateController);

function TypeCreateController(Type, $state) {

    var vm = this;
    
    vm.type = new Type();
    
    vm.saveType = function() {
        vm.errors = undefined;
        vm.type.$save({}, function() {
            $state.go('type.show', {id: vm.type.id});
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
