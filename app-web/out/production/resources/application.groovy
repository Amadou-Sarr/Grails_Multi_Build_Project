

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/html5',          access: ['permitAll']],
	[pattern: '/html5.html',     access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
// Rest JWT Configation of filters
		[pattern: '/api/v1.0/**', filters: 'anonymousAuthenticationFilter,restTokenValidationFilter,' +
				'restExceptionTranslationFilter,filterInvocationInterceptor'],

		[pattern: '/api/**', filters: 'JOINED_FILTERS,' +
				'-anonymousAuthenticationFilter,' +
				'-exceptionTranslationFilter,' +
				'-authenticationProcessingFilter,' +
				'-securityContextPersistenceFilter,' +
				'-rememberMeAuthenticationFilter'],

	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS, -restTokenValidationFilter, -restExceptionTranslationFilter'],

		//Rest JWT config validation request
rest{
	token{
		validation{
			enableAnonymousAccess : true
		}
	}
}
]

grails.plugin.springsecurity.userLookup.userDomainClassName = 'app.admin.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'app.admin.security.UserRole'
grails.plugin.springsecurity.authority.className = 'app.admin.security.Role'

//
grails.resources.pattern = '/**'

// Adding cross origins enabled code
grails :
      cors:
        enabled = true