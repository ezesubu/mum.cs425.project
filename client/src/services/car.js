'use strict';

function CarSvc($q, Restangular){
    let resource = Restangular.all('api/cars');


    function fnGetAll() {
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
    function fnFind(id) {
        let def = $q.defer();
        let promise = resource.one(id).get();

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
      fnFind: fnFind
  }

}



const serviceConfig = [
  '$q',
  'Restangular',
  CarSvc
]

angular.module('car')
  .factory('CarSvc', serviceConfig)

module.exports = {
  name: 'CarSvc',
  factory: serviceConfig
}
