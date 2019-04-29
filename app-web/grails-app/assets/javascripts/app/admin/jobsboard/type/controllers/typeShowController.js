//= wrapped

angular
    .module("app.admin.jobsboard.type")
    .controller("TypeShowController", TypeShowController);

function TypeShowController(Type, $stateParams, $state) {
    var vm = this;

    Type.get({id: $stateParams.id}, function(data) {
        vm.type = new Type(data);
    }, function() {
        $state.go('type.list');
    });

    vm.delete = function() {
        vm.type.$delete(function() {
            $state.go('type.list');
        }, function() {
            //on error
        });
    };

}
