@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page


@template.layout.page(
    content = @`
        <h2>Список постов</h2>
      
      	@if(page.getPosts().isEmpty())
      		<p>Список постов пуст</p>
      	@else
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
              		@for(var post : page.getPosts())
                		<tr>
                  			<td><a href="${NamedRoutes.postPath(post.getId())}">${post.getId()}</a></td>
                  			<td>${post.getName()}</td>
                  			<td>${post.getBody()}</td>
                		</tr>
              		@endfor
            	</tbody>
          		</table>
        	</div>
      	@endif
      	
      	@if(page.getPageNumber() > 1)
        	<div>
          		<a href="${NamedRoutes.postsPath(page.getPageNumber()-1)}">Предыдущая страница</a>
        	</div>
        @endif
        	
      	<div>
       		<a href="${NamedRoutes.postsPath(page.getPageNumber()+1)}">Следующая страница</a>
       	</div>
    `
)