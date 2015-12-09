console.clear();

var app = angular.module('myApp', ['ui.router']);

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

      $urlRouterProvider.otherwise('/');

      $stateProvider
        .state('home', {
          url: '/',
          templateUrl: 'partials/home.html',
          data: {
            pageTitle: 'Home'
          }
        })
        .state('about', {
          url: '/about',
          templateUrl: 'partials/about.html',
          data: {
            pageTitle: 'About'
          }
        })
        .state('login', {
          url: '/login',
          templateUrl: 'partials/login.html',
          data: {
            pageTitle: 'Log In'
          },
          controller: 'LogInController'
        });
    }
]);

app.directive('updateTitle', ['$rootScope', '$timeout',
      function($rootScope, $timeout) {
        return {
          link: function(scope, element) {

            var listener = function(event, toState) {

              var title = 'Home';
              if (toState.data && toState.data.pageTitle) title = toState.data.pageTitle;

              $timeout(function() {
                element.text(title);
              }, 0, false);
            };

            $rootScope.$on('$stateChangeSuccess', listener);
          }
        };
      }
]);

//create service: Page and modify from controllers.
app.factory('Page', function(){
	  var title = 'Home';
	  return {
	    title: function() { return title; },
	    setTitle: function(newTitle) { title = newTitle; }
	  };
	});