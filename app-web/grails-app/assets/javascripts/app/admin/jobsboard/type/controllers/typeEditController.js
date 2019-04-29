//= wrapped

angular
    .module("app.admin.jobsboard.type")
    .controller("TypeEditController", TypeEditController);

function TypeEditController(Type, $stateParams, $state) {
    var vm = this;

    

    Type.get({id: $stateParams.id}, function(data) {
        vm.type = new Type(data);
    }, function() {
        vm.errors = [{message: "Could not retrieve type with ID " + $stateParams.id}];
    });

    vm.updateType = function() {
        vm.errors = undefined;
        vm.type.$update(function() {
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
