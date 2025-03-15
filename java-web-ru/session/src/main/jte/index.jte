@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <main>
                @if(page != null && page.getName() != null)
        			<h3>${String.valueOf(page.getName())}</h3>
        			
        			<form action="${NamedRoutes.logoutPath()}" method="post">
        				<div class="mb-3">
                			<input type="submit" value="Выйти" />
               			</div>
      			  	</form>
      			@else
      			  	<a href="${NamedRoutes.buildSessionPath()}">Войти</a>
      			@endif
            </main>
        </div>
    `
)