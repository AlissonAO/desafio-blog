import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css']
})
export class PostDetailsComponent implements OnInit {
  currentPost = null;
  message = '';

  constructor(
    private postService: PostService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.message = '';
    this.getPost(this.route.snapshot.paramMap.get('id'));
  }

  getPost(id) {
    this.postService.get(id)
      .subscribe(
        data => {
          this.currentPost = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  updatePublished(status) {
    const data = {
      titulo: this.currentPost.titulo,
      mensagem: this.currentPost.mensagem,
      published: status
    };

    this.postService.update(this.currentPost.id, data)
      .subscribe(
        response => {
          this.currentPost.published = status;
          console.log(response);
        },
        error => {
          console.log(error);
        });
  }

  updatePost() {
    this.postService.update(this.currentPost.id, this.currentPost)
      .subscribe(
        response => {
          console.log(response);
          this.message = 'The post was updated successfully!';
        },
        error => {
          console.log(error);
        });
  }

  deletePost() {
    this.postService.delete(this.currentPost.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/posts']);
        },
        error => {
          console.log(error);
        });
  }
}
