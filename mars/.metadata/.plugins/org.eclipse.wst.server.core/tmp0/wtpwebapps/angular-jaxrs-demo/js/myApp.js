console.clear();

var app = angular.module('myApp', ['ui.router']);

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
          views: {
            'nav-view': {
              templateUrl: 'partials/home.html',
              controller: ''
            },
            'info-view': {
              templateUrl: '',
              controller: ''
            }
          },         
          data: {
            pageTitle: 'Home'
          }
        })
        .state('about', {
          url: '/about',
          views: {
            'nav-view': {
              templateUrl: 'partials/about.html',
              controller: ''
            },
            'info-view': {
              templateUrl: '',
              controller: ''
            }
          },
          data: {
            pageTitle: 'About'
          }          
        })
        .state('converters', {
          url: '/converters',
          views: {
            'nav-view': {
              templateUrl: 'partials/converters.html',
              controller: 'getWeatherController'
            },
            'info-view': {
              templateUrl: '',
              controller: ''
            }
          },
          data: {
            pageTitle: 'Converters'
          }
        })
        .state('contact', {
          url: '/contact',
          views: {
            'nav-view': {
              templateUrl: 'partials/contact.html',
              controller: ''
            },
            'info-view': {
              templateUrl: '',
              controller: ''
            }
          },
          data: {
            pageTitle: 'Contact'
          }
        })
        .state('search', {
          url: '/search',
          views: {
            'nav-view': {
              templateUrl: 'partials/search.html',
              controller: ''
            },
            'info-view': {
              templateUrl: '',
              controller: ''
            }
          },
          data: {
            pageTitle: 'Search'
          }
        })
        .state('login', {
          url: '/login',
          views: {
            'nav-view': {
              templateUrl: 'partials/login.html',
              controller: 'LogInController'
            },
            'info-view': {
              templateUrl: '',
              controller: ''
            }
          },
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

app.directive('listener', ['$window', '$rootScope', function( $window, $rootScope ){
  return {
    restrict: 'A',
    link: function( scope, element, attr ){
      // bind event listeners here
      angular.element($window).bind('MAP_Loaded', eventListener);
      angular.element($window).bind('MAP_Ready', eventListener);
      angular.element($window).bind('MAP_IrrigationZoneSelect', eventListener);
      angular.element($window).bind('MAP_TaskSelect', eventListener);
      angular.element($window).bind('MAP_DrawnShapeClosed', eventListener);
      angular.element($window).bind('MAP_ClearedDrawnShapes', eventListener);      
      angular.element($window).bind('NG_ShowZoneInfo', eventListener);
      angular.element($window).bind('MAP_ReportShapeSelect', eventListener);
      angular.element($window).bind('PN_UpdateUI', eventListener);
      angular.element($window).bind('PN_UpdateNotificationUI', eventListener);
      angular.element($window).bind('PN_SignalRConnDone', eventListener);

      function eventListener( event, data ){
        if( $rootScope.DebugMode ){
          console.log("(Angular) I'm listening! - " + event.type);
          console.log(data);
          console.log('// end event');
        }

        $rootScope.$broadcast(event.type, data);
      }
    }
  };
}]);

//create service: Page to modify from controllers.
app.factory('Page', function(){
	  var title = 'Home';
	  return {
	    title: function() { return title; },
	    setTitle: function(newTitle) { title = newTitle; }
	  };
	});

/* Notification Service */
app.factory('NotifyService', ['$rootScope', '$timeout',
  function($rootScope, $timeout) {
    var currentMessage = '';
    var currentType = '';
    var state = '';
    var notifyTimeout = null;

    return {
      getType: function(){
        return currentType;
      },
      getState: function(){
        return currentType + ' ' + state;
      },
      setMessage: function(message, type) {
        if( notifyTimeout != null ){
          $timeout.cancel(notifyTimeout);
          notifyTimeout = null;
        }

        //console.log(message);
        currentMessage = message;
        currentType = type || '';
        
        state = 'active';
        notifyTimeout = $timeout(function(){ state = ''; }, 5000);
      },
      getMessage: function() {
        return currentMessage;
      }
    };
  }]
);

app.service('DataService', ['$http', '$rootScope', 'NotifyService', '$interval',
  function($http, $rootScope, NotifyService, $interval){
    var request_count = 0;
    var requestRefArray = [];
    var requestIdCounter = 0;
    var pingInterval = 75000;
    var pingTimeout = 15000;
    var pingRef = null;

    $rootScope.$on('LOADINGMODAL_OPEN', function(){
      if( $rootScope.DebugMode ){
        console.log('(load open) requests: ' + request_count );
      }
    });
    $rootScope.$on('SENDINGMODAL_OPEN', function(){
      if( $rootScope.DebugMode ){
        console.log('(send open) requests: ' + request_count );
      }
    });
    $rootScope.$on('LOADINGMODAL_CLOSE', function(){
      if( $rootScope.DebugMode ){
        console.log('(load close) requests: ' + request_count );
      }
    });
    $rootScope.$on('SENDINGMODAL_CLOSE', function(){
      if( $rootScope.DebugMode ){
        console.log('(send close) requests: ' + request_count );
      }
    });

    // set default ajax options
    $.ajaxSetup({
      beforeSend: function(jqXHR){
        jqXHR._id = requestIdCounter++;
        requestRefArray[ jqXHR._id ] = jqXHR;
      },
      complete: function(jqXHR){
        delete requestRefArray[ jqXHR._id ];
      }
    });

    return {
      requestCount: request_count,
      ping: function(){
        var parentThis = this;
        var promise = $http({
          url: $rootScope.APIRootUrl + '/api/Ping',
          method: 'GET',
          timeout: pingTimeout
        });

        promise.success(function(data, status, headers, config, statusText){
          if( ! $rootScope.isOnline ){
            $rootScope.isOnline = true;
            NotifyService.setMessage('Your connection has been restored.', 'success');
          }
        });

        promise.error(function(data, status, headers, config, statusText){
          if( $rootScope.isOnline ){
            $rootScope.isOnline = false;
            NotifyService.setMessage('You have lost your connection.', 'error');
            
            // abort all requests
            parentThis.abortAll();
          }
        });
      },
      startPing: function(){
        var parentThis = this;
        this.ping();
        pingRef = $interval(function(){ parentThis.ping(); }, pingInterval);
      },
      stopPing: function(){
        $interval.cancel( pingRef );
      },
      convertDate: function( dateString ) {
        // based on code from: http://jsfiddle.net/kQZw8/6/light/
        var regexIso8601 = /^(\d{4}|\+\d{6})(?:-(\d{2})(?:-(\d{2})(?:T(\d{2}):(\d{2}):(\d{2})\.(\d{1,})(Z|([\-+])(\d{2}):(\d{2}))?)?)?)?$/;

        // check that the string looks like a date
        var match = null;
        if( typeof dateString === 'string' && ( match = dateString.match(regexIso8601) ) ) {
          var milliseconds = Date.parse(match[0]);
          if ( ! isNaN(milliseconds) ) {
            return new Date(milliseconds);
          }
        } else {
          return dateString;
        }
      },
      unique: function(data, key) {
        var result = [];
        for ( var i = 0; i < data.length; i++ ) {
          var value = data[i][key];
          
          if ( result.indexOf(value) == -1 ) {
            result.push(value);
          }
        }
        return result;
      },
      abortAll: function(){
        angular.forEach(requestRefArray, function (value, key) {
          //console.log('aborting all requests:');
          //console.log('  ' + key + ': ' + value);
          this.requestRefArray[ key ].abort();
          //console.log('  request queue:');
          //console.log(requestRefArray);
        }, this);
      },
      get: function( targetURL, successCallback, errorCallback, useModal ) {
        if( useModal !== false ){
          useModal = true;
        }

        if( $rootScope.DebugMode ){
          console.log('get request: ' + targetURL );
        }

        var options = {
          url: targetURL,
          method: 'GET',
          'useModal': useModal
        };
        
        if( $rootScope.Token ){
          options.headers = {"Authorization": "Bearer " + $rootScope.Token, "Accept": "*/*"};

          if( $rootScope.producerId != null ){
            options.headers[ 'fs.producerid' ] = $rootScope.producerId;
          }
        }

        var promise = $http(options);
        if( useModal === true ){
          request_count++;
          $rootScope.$broadcast('LOADINGMODAL_OPEN');
        }
        
        promise.success(function(data){
          if( options.useModal === true ){
            request_count--;
            if( ! request_count ) $rootScope.$broadcast('LOADINGMODAL_CLOSE');
          }
          if( successCallback ) successCallback(data);
        });
        promise.error(function(errorMsg, status, headers, config){
          if( options.useModal === true ){
            request_count--;
            if( ! request_count ) $rootScope.$broadcast('LOADINGMODAL_CLOSE');
          }
          if( errorCallback ) errorCallback(errorMsg);

          options.errorMsg = errorMsg;
          options.status = status;
          options.respHeaders = headers;
          options.config = config;
          $rootScope.$broadcast('DATASERVICE_ERROR', options );

          if( errorMsg.Message == 'Authorization has been denied for this request.' || status == 401 ){
            NotifyService.setMessage('You must be logged in to view this information.', 'error');
            $rootScope.logout();
          }
        });

        return promise;
      },
      http: function( options, successCallback, errorCallback, useModal ) {

        if( useModal !== false ){
          useModal = true;
        }
        
        if( typeof options !== 'object' ){
          options = {
            url: options,
            method: 'GET',
            headers: {}
          };
        }

        if( $rootScope.DebugMode ){
          console.log('http request (' + options.method + '): ' + options.url );
        }

        //console.log( options.data );
        if( options.headers === undefined ){
          options.headers = {};
        }
        if( $rootScope.Token ){
          options.headers.Authorization = "Bearer " + $rootScope.Token;
          
          if( $rootScope.producerId != null ){
            options.headers[ 'fs.producerid' ] = $rootScope.producerId;
          }
        }
        options.headers.Accept = "*/*";
        options.useModal = useModal;

        var promise = $http( options );

        if( useModal === true ){
          request_count++;
          switch( options.method ){
            case 'POST':
            case 'PUT':
            case 'DELETE':
            case 'REMOVE':
              $rootScope.$broadcast('SENDINGMODAL_OPEN');
              //console.log(options.data);
              break;
            case 'GET':
            default:
              $rootScope.$broadcast('LOADINGMODAL_OPEN');
          }
        }

        promise.success(function(data, status, headers, config){
          if( options.useModal === true ){
            request_count--;
            if( ! request_count ){
              if( config.method == 'GET' ){
                $rootScope.$broadcast('LOADINGMODAL_CLOSE');
              } else {
                $rootScope.$broadcast('SENDINGMODAL_CLOSE');
              }
            }
          }
          if( successCallback ) successCallback(data);
        });

        promise.error(function(errorMsg, status, headers, config){
          if( options.useModal === true ){
            request_count--;
            if( ! request_count ){
              if( config.method == 'GET' ){
                $rootScope.$broadcast('LOADINGMODAL_CLOSE');
              } else {
                $rootScope.$broadcast('SENDINGMODAL_CLOSE');
              }
            }
          }
          
          if( errorCallback ) errorCallback(errorMsg);

          options.errorMsg = errorMsg;
          options.status = status;
          options.respHeaders = headers;
          options.config = config;
          $rootScope.$broadcast('DATASERVICE_ERROR', options );

          if( errorMsg.Message == 'Authorization has been denied for this request.' || status == 401 ){
            NotifyService.setMessage('You must be logged in to view this information.', 'error');
            $rootScope.logout();
          }
        });
      }
    };
  }]
);

