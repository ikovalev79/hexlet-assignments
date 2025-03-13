@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
    content = @`
        <h1>Создание новой статьи</h1>
        
        @if(page.getErrors() != null)
 			<ul>
          		@for(var validator : page.getErrors().values())
              		@for(var error : validator)
                  		<li>${error.getMessage()}</li>
              		@endfor
          		@endfor
      		</ul>
  		@endif

        <form action="/articles" method="post">
  			<table >
  			  <tr>
  			    <td>Название статьи</td>
  			    <td><input type="text" required name="title" value="${page.getTitle()}" /></td>
  			  </tr>
  			  <tr>
  			    <td>Содержание статьи</td>
  			    <td><textarea rows="10" cols="45" id="content" required name="content" value="${page.getContent()}"></textarea></td>
  			  </tr>
  			</table>
  			
  			<input type="submit" value="Создать" />
		</form>
    `
)