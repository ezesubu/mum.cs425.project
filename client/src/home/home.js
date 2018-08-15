'use strict';
var angular = require('angular');

require('../css/home.css')

function homeCtrl($scope, $sessionStorage, $auth, $state, $filter,$uibModal, LoginSvc, CarSvc) {
  $scope.title = 'client';
  $scope.navBar = require('../includes/navbar.html')
  $scope.links = $state.get()
    .filter(x => x.name.startsWith('home.'))
    .map(x => {
      return {
        title: x.url.slice(1),
        link: $state.href(x.name)
      }
    });

    let promise = CarSvc.fnGetAll();

    promise.then(function (objData) {
      $scope.cars = objData;
    });

    $scope.signout = signout;
    $scope.addToCart = addToCart;


  function signout(){
    LoginSvc.logout()
    $state.go('login')
  }

  function addToCart(id){
      $state.go('booking', {car: id});

  }


    function openComponentModal() {

        $uibModal.open({
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'stackedModal.html',
            controller: ['$scope', '$uibModalInstance', '$location',
                function($scope, $uibModalInstance, $location) {
                    $scope.cancel = function () {
                        $uibModalInstance.dismiss();
                    };

                    $scope.ok= function () {
                        $location.path("/myCart");
                    };
                }
            ]
        });
    };

}

var stateConfig = {
  name: 'home',
  url: '/home',
  templateUrl: require('./home.html'),
  controller: 'homeCtrl'
};

homeCtrl.$inject = [
  '$scope',
  '$sessionStorage',
  '$auth',
  '$state',
  '$filter',
  '$uibModal',
  'LoginSvc',
  'CarSvc'
]

function routeConfig($stateProvider) {
  $stateProvider.state(stateConfig)
}

angular.module('car')
  .controller('homeCtrl', homeCtrl)
  .config([ '$stateProvider', routeConfig ])

module.exports = stateConfig;
