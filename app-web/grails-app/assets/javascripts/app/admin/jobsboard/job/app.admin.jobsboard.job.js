//= wrapped
//= require /angular/angular 
//= require /angular/angular-ui-router
//= require /angular/angular-resource
//= require /app/admin/jobsboard/core/app.admin.jobsboard.core
//= require /app/admin/jobsboard/type/app.admin.jobsboard.type
//= require /app/admin/jobsboard/tag/app.admin.jobsboard.tag
//= require /app/admin/jobsboard/publisher/app.admin.jobsboard.publisher
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("app.admin.jobsboard.job", [
    "ui.router",
    "ngResource",
    "app.admin.jobsboard.core",
    "app.admin.jobsboard.type",
    "app.admin.jobsboard.tag",
    "app.admin.jobsboard.publisher"
]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('job', {
            url: "/job",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('job.list', {
            url: "",
            templateUrl: "/app/admin/jobsboard/job/list.html",
            controller: "JobListController as vm"
        })
        .state('job.create', {
            url: "/create",
            templateUrl: "/app/admin/jobsboard/job/create.html",
            controller: "JobCreateController as vm"
        })
        .state('job.edit', {
            url: "/edit/:id",
            templateUrl: "/app/admin/jobsboard/job/edit.html",
            controller: "JobEditController as vm"
        })
        .state('job.show', {
            url: "/show/:id",
            templateUrl: "/app/admin/jobsboard/job/show.html",
            controller: "JobShowController as vm"
        });
}
