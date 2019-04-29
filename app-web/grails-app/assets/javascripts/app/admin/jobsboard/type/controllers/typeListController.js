//= wrapped

angular
    .module("app.admin.jobsboard.type")
    .controller("TypeListController", TypeListController);

function TypeListController(Type) {
    var vm = this;

    var max = 10, offset = 0;

    Type.list({max: max, offset: offset}, function(data) {
        vm.typeList = data;
    });
}
