import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-posts-list',
  templateUrl: './posts-list.component.html',
  styleUrls: ['./posts-list.component.css']
})
export class PostsListComponent implements OnInit {

  posts: any;
  currentPost = null;
  currentIndex = -1;
  title = '';

  constructor(private postService: PostService) { }

  ngOnInit() {
    this.retrievePosts();
  }

  retrievePosts() {
    this.postService.getAll()
      .subscribe(
        data => {
          this.posts = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList() {
    this.retrievePosts();
    this.currentPost = null;
    this.currentIndex = -1;
  }

  setActivePost(post, index) {
    this.currentPost = post;
    this.currentIndex = index;
  }

  removeAllPosts() {
    this.postService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.retrievePosts();
        },
        error => {
          console.log(error);
        });
  }

  searchTitle() {
    this.postService.findByTitle(this.title)
      .subscribe(
        data => {
          this.posts = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
}
