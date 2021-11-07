import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {
  post = {
    titulo: '',
    mensagem: '',
    nomePost: ''
  };
  submitted = false;

  constructor(private postService: PostService) { }

  ngOnInit() {
  }

  savePost() {
    const data = {
      nomePost: this.post.titulo,
      mensagem: this.post.mensagem,
      titulo: this.post.titulo
    };

    this.postService.create(data)
      .subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(error);
        });

    this.submitted = true;
  }

  newPost() {
    this.submitted = false;
    this.post = {
      titulo: '',
      mensagem: '',
      nomePost: ''
    };
  }

}
