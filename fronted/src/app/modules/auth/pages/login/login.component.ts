import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/models/login-request';
import { AuthService } from 'src/app/services/auth.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  showAlert = false;
  loginError: string="";
  loginSuccess: string = "";


  loginForm = this.fb.group({
    email:['', [Validators.required,Validators.email]],
    password:['',[Validators.required, Validators.minLength(8)]]
  })

  constructor(private fb: FormBuilder, private router:Router, private authService:AuthService,
    private tokenService:TokenService){}

  ngOnInit(): void {

  }
  get email(){
    return this.loginForm.controls.email;
  }

  get password()
  {
    return this.loginForm.controls.password;
  }

  login(){
    if(this.loginForm.valid){
      this.loginError = '';
      this.authService.login(this.loginForm.value as LoginRequest).subscribe({
        next: (response) => {
          console.log("¡Se ha hecho clic en el botón de inicio de sesión!");
          console.log(response);
          //seteo en el localEstore el token, rol y email del usuario logueado
          this.tokenService.setToken(response.token);
          this.tokenService.setRol(response.rol);
          this.tokenService.setEmail(response.email);
          this.tokenService.setCustomersUuId(response.id);
          this.loginSuccess = "Inicio de sesión exitoso";
          // Mostrar el mensaje por 1 segundos y luego borrarlo
          setTimeout(() => {
            this.loginSuccess = "";
          }, 1000);
        },
        error: (errorData) => {
          console.log(errorData);
          this.showAlert = true;
          this.loginError = errorData;
          setTimeout(() =>{
            this.showAlert = false;
            this.loginForm.reset();
          }, 1000);
        },
        complete: () => {
          console.info("Login completo");
          setTimeout(() => {

            this.router.navigateByUrl('home/general');
          },2000);

          this.loginForm.reset();

        }
      })
    }
    else{
      this.loginForm.markAllAsTouched();
      alert("Error al ingresar los datos.");
    }
  }


}
