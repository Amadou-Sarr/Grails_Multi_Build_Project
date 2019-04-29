//= wrapped

angular
    .module("app.admin.jobsboard.core")
    .factory("domainConversion", domainConversion);

function domainConversion($injector) {
    var domainCache = {};
    return function(domainClass, property) {
        return function(domain) {
            if (domain.hasOwnProperty(property)) {
                var Domain;
                if (!domainCache[domainClass]) {
                    domainCache[domainClass] = $injector.get(domainClass);
                }
                Domain = domainCache[domainClass];
                domain[property] = new Domain(domain[property]);
            }
            return domain;
        };
    };
}