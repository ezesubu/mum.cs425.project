'use strict';

// angular
var angular = require('angular');
require('@uirouter/angularjs');
require('ngstorage');

// bootstrap and deps
require('bootstrap/dist/css/bootstrap.css');
require('bootstrap/dist/js/bootstrap.js');
require('ui-bootstrap4/index');
require('font-awesome/css/font-awesome.css');
require('popper.js/dist/popper.js');
require('jquery/dist/jquery.js');
require('restangular');
require('satellizer');



// util
function importAll(r) {
  r.keys().forEach(r);
}

// begin module
angular.module('car', ['ui.router', 'ngStorage', 'restangular', 'satellizer', 'ui.bootstrap']);

// app deps
require('./constants.js');

function addAuthGuard($rootScope, $location, $sessionStorage){
  // redirect to login page if not logged in and trying to access a restricted page
  // $rootScope.$on('$locationChangeStart', function (event, next, current) {
  //   if ( $location.path() != '/login' && !$sessionStorage.user ) {
  //     $location.path('/home');
  //   }
  // });
}

function setDefaults($urlRouterProvider, RestangularProvider,carConstants , $authProvider){
   $urlRouterProvider.otherwise('/home')
   RestangularProvider.setBaseUrl(carConstants.base_url);
   RestangularProvider.setFullResponse(true);
   $authProvider.loginUrl = carConstants.base_url + '/login';

}


// services
importAll(require.context('./services', false, /\.js$/));

// filters
importAll(require.context('./filters', false, /\.js$/));

// directives
importAll(require.context('./directives', false, /\.js$/))

// routers
importAll(require.context('./login', false, /.js$/))
importAll(require.context('./home', true, /.js$/))
importAll(require.context('./car', true, /.js$/))

angular.module('car')
  .config(['$urlRouterProvider', 'RestangularProvider', 'carConstants', '$authProvider', setDefaults])
  .run(['$rootScope', '$location', '$sessionStorage', addAuthGuard])
/*
// https://github.com/angular-ui/ui-router/wiki/Frequently-Asked-Questions
// debugging
angular.module('car').run(function($rootScope) {
  $rootScope.$on("$stateChangeError", console.log.bind(console));
});
*/

module.exports = angular.module('car');
// load index
require('./index.html')




