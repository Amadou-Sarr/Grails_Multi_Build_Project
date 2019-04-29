//= wrapped

angular
    .module("app.admin.jobsboard.core")
    .factory("domainListConversion", domainListConversion);

function domainListConversion($injector) {
    var delegateCache = {};
    return function(domainClass, property, delegateFunction) {
        return function(domainList) {
            if (!delegateCache[delegateFunction]) {
                delegateCache[delegateFunction] = $injector.get(delegateFunction);
            }
            return domainList.map(delegateCache[delegateFunction](domainClass, property));
        };
    };
}