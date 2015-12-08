console.clear();

var app = angular.module('myApp', ['ui.router']);

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

      $urlRouterProvider.otherwise('/home');

      $stateProvider
        .state('home', {
          url: '/',
          templateUrl: 'index.html',
          data: {
            pageTitle: 'Canvas World'
          }
        })
        .state('about', {
          url: '/about',
          templateUrl: 'partials/left-sidebar.html',
          data: {
            pageTitle: 'Left'
          }
        })
        .state('credits', {
          url: '/credits',
          templateUrl: 'partials/main-info.html',
          data: {
            pageTitle: 'Right Main'
          }
        })
        .state('index', {
        url: '',
        templateUrl: '',
        data: {
          pageTitle: 'Home'
        }
      });
    }
]);

app.directive('updateTitle', ['$rootScope', '$timeout',
      function($rootScope, $timeout) {
        return {
          link: function(scope, element) {

            var listener = function(event, toState) {

              var title = 'Default Title';
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