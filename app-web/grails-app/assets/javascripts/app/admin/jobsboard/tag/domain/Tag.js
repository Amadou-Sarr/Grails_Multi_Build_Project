//= wrapped
//= require /angular/angular-resource

angular
    .module("app.admin.jobsboard.tag")
    .factory("Tag", Tag);

function Tag($resource) {
    var Tag = $resource(
        "tag/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true},
         "get": {method: 'GET'}}
    );

    Tag.list = Tag.query;

    Tag.prototype.toString = function() {
        return 'app.admin.jobsboard.Tag : ' + (this.id ? this.id : '(unsaved)');
    };

    return Tag;
}
