'use strict';
var angular = require('angular');

require('../css/login.css')

function loginCtrl($scope, $sessionStorage, $state, $filter, LoginSvc,$auth, $uibModal ) {
  $scope.progress = false;
  $scope.title = 'client';

  $scope.copyrightYear = new Date().getFullYear();
  LoginSvc.logout();
  $scope.submitForm = function(isValid){
    if ( isValid ) {
        $scope.progress = true;
        $auth.login({username: $scope.username, password: $scope.password}).then(function () {
           $state.go('home');
        },
        function () {
            console.log("Error", "User o Password incorrect", "error");
        });
        $scope.progress = false;
    }
  }
}

var stateConfig = {
  name: 'login',
  url: '/login',
  templateUrl: require('./login.html'),
  controller: 'loginCtrl'
};

loginCtrl.$inject = [
  '$scope',
  '$sessionStorage',
  '$state',
  '$filter',
  'LoginSvc',
  '$auth',
  '$uibModal'
]

function routeConfig($stateProvider) {
  $stateProvider.state(stateConfig)
}

angular.module('car')
  .controller('loginCtrl', loginCtrl)
  .config([ '$stateProvider', routeConfig ])

module.exports = stateConfig;
