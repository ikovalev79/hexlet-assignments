@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage
@param PostPage page


@template.layout.page(
    content = @`
        <h2>Описание поста</h2>
      
       	<div>
    		<table width="500" cellspacing="0" cols="3">
          	<thead>
            	<tr>
              		<th>Id</th>
              		<th>Name</th>
              		<th>Body</th>
            	</tr>
          	</thead>
          	<tbody>
            	<tr>
              		<td>${page.getPost().getId()}</td>
              		<td>${page.getPost().getName()}</td>
              		<td>${page.getPost().getBody()}</td>
            	</tr>
          	</tbody>
        	</table>
        </div>
        
        <div>
          	<p>
        		<a href="${NamedRoutes.postsPath()}">Список постов</a>
       		</p>
        </div>
    `
)