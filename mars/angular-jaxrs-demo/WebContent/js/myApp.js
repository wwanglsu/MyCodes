console.clear();

var app = angular.module('myApp', ['ui.router']);
var wsHostUrl = "http://localhost:8080/angular-jaxrs-demo/";

/* calling order:
    1 app.config();
    2 app.run();
    3 directive's compile functions (if they are found in the dom)
    4 app.controller()
    5 directive's link functions (again, if found)  
*/

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
        .state('converters', {
          url: '/converters',
          templateUrl: 'partials/converters.html',
          data: {
            pageTitle: 'Converters'
          }
        })
        .state('contact', {
          url: '/contact',
          templateUrl: 'partials/contact.html',
          data: {
            pageTitle: 'Contact'
          }
        })
        .state('search', {
          url: '/search',
          templateUrl: 'partials/search.html',
          data: {
            pageTitle: 'Search'
          }
        })
        .state('login', {
          url: '/login',
          templateUrl: 'partials/login.html',
          data: {
            pageTitle: 'Log In'
          }
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

//create service: Page to modify from controllers.
app.factory('Page', function(){
	  var title = 'Home';
	  return {
	    title: function() { return title; },
	    setTitle: function(newTitle) { title = newTitle; }
	  };
	});