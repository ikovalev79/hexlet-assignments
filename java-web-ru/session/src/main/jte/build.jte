@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <form action="${NamedRoutes.loginPath()}" method="post">
                <h3>Вход</h3>
                
                <div class="mb-3">
                	<input type="text" placeholder="name" name="name" value="${page.getName()}" />
               	</div>
               	<div class="mb-3">
  					<input type="password" placeholder="password" name="password" />
  				</div>
  				<div class="mb-3">
                	<input type="submit" />
               	</div>
            </form>
            
            @if(page.getError() != null)
        		<ul>
        			<li>${page.getError()}</li>
        		</ul>
      		@endif
        </div>
    `
)