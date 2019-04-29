//= wrapped
//= require /angular/angular-resource

angular
    .module("app.admin.jobsboard.type")
    .factory("Type", Type);

function Type($resource) {
    var Type = $resource(
        "type/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true},
         "get": {method: 'GET'}}
    );

    Type.list = Type.query;

    Type.prototype.toString = function() {
        return 'app.admin.jobsboard.Type : ' + (this.id ? this.id : '(unsaved)');
    };

    return Type;
}
