//= wrapped
//= require /angular/angular
//= require /angular/ui-bootstrap-tpls
//= require /angular/angular-ui-router
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree templates

angular.module("app.web.index", [
    "app.web.core",
    "ui.bootstrap.dropdown",
    "ui.bootstrap.collapse",
    "ui.router"
])
.config(config);

function config($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('index', {
            url: "/",
            templateUrl: "/app/web/index/landing.html"
        })
       .state('jobs', {
    url: "/jobs",
         templateUrl: "/app/web/index/jobs.html",
         controller: "JobListController as vm"
    })
;
    $urlRouterProvider.otherwise('/');
}
