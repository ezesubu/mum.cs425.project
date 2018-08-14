'use strict';

function CustomerCartSvc($q, Restangular){

    let resource = Restangular.one('api/carts');

    function fnGetAll () {
        let def = $q.defer();

          let promise = resource.customGET('');

        promise.then(
            function (objResponse) {
                def.resolve(angular.copy(objResponse.data));
            }, function (objResponse) {
                def.resolve(angular.copy(objResponse.data));
            }
        );

        return def.promise;
    }

    function fnAdd(formData) {
        let def = $q.defer();

        let promise = resource.customPOST(formData);

        promise.then(
            function (objResponse) {
                def.resolve(angular.copy(objResponse.data));
            }, function (objResponse) {
                def.resolve(angular.copy(objResponse.data));
            }
        );

        return def.promise;
    }

    return {
        fnGetAll: fnGetAll,
        fnAdd: fnAdd
    }

}



const serviceConfig = [
    '$q',
    'Restangular',
    CustomerCartSvc
]

angular.module('car')
    .factory('CustomerCartSvc', serviceConfig)

module.exports = {
    name: 'CustomerCartSvc',
    factory: serviceConfig
}
