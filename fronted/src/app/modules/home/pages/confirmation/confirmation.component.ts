import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription, interval } from 'rxjs';
import { ConfirmResponse } from 'src/app/models/confirm-response';
import { AuthService } from 'src/app/services/auth.service';
import { InactivityService } from 'src/app/services/inactivity.service';
import { LoansApplicationService } from 'src/app/services/loans-application.service';
import { TokenService } from 'src/app/services/token.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {
  confirmResponse!: ConfirmResponse;

  private updateIntervalMinutes = 3;
  private updateIntervalMillis = this.updateIntervalMinutes * 60 * 1000;
  private userActivitySubscription: Subscription | undefined;

  constructor(private tokenService: TokenService, private authService: AuthService,
    private inactivityService: InactivityService, private loanService: LoansApplicationService,
    private activatedRoute: ActivatedRoute, private router: Router){}

  ngOnInit(): void {
    if (this.tokenService.isLoggued()) {
      // Actualizar token cada 3 minutos
      this.userActivitySubscription = interval(this.updateIntervalMillis).subscribe(() => {
        this.updateToken();
      });
      this.inactivityService.startInactivityTimer();
      this.getLoanApplication();
    }

  }

  getLoanApplication():void{
    console.log("Entrando al metodo getLoanApplication")

    const id = this.tokenService.getLoanApplicationId();

    if(id){
      this.loanService.findLoanApplication(id).subscribe(
      {
        next: response =>{
          console.log(response);
          this.confirmResponse = response;
        },
        error: err =>{
          console.log(err);

        },
        complete: () => {
          console.log("Respuesta completa");

        }
      }
      )
    }

  }
  confirm(){
     Swal.fire({
              //position: 'center',
              icon: 'success',
              title: 'Gracias por elegirnos!',
              html: '<div style="font-size: 18px; color: #333;">Tu solicitud se ha cargado exitosamente y se encuentra en revisión. Nos comunicaremos contigo a la brevedad.</div>',
              confirmButtonText: 'OK'


            }).then((result) => {
              if(result.isConfirmed){
                setTimeout(() => {
                  this.router.navigateByUrl('/home');
                },1000);
              }
            })
  }


  ngOnDestroy(): void {
    // Cancelar la suscripción cuando se destruye el componente
    if (this.userActivitySubscription) {
      this.userActivitySubscription.unsubscribe();
    }
  }

  private updateToken(): void {
    this.authService.getCurrentSession().subscribe(
      updatedSession => {
        console.log("Token viejo: " + this.tokenService.getToken())
        this.tokenService.setToken(updatedSession.token);
        console.log("Token nuevo:" + this.tokenService.getToken());
      },
      error => {
        console.error('Error al actualizar el token:', error);
      }

    )
  }
}
