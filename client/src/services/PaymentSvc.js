'use strict';

function PaymentSvc($q, Restangular){

    let resource = Restangular.one('api/payments');


    function fnAdd(data) {
        let def = $q.defer();

        let promise = resource.customPOST(data);

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
        fnAdd: fnAdd
    }

}



const serviceConfig = [
    '$q',
    'Restangular',
    PaymentSvc
]

angular.module('car')
    .factory('PaymentSvc', serviceConfig)

module.exports = {
    name: 'PaymentSvc',
    factory: serviceConfig
}
