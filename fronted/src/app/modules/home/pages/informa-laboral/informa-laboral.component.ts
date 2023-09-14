import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription, interval } from 'rxjs';
import { JobInformationRequest } from 'src/app/models/job-information-request';
import { AuthService } from 'src/app/services/auth.service';
import { InactivityService } from 'src/app/services/inactivity.service';
import { LoansApplicationService } from 'src/app/services/loans-application.service';
import { TokenService } from 'src/app/services/token.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-informa-laboral',
  templateUrl: './informa-laboral.component.html',
  styleUrls: ['./informa-laboral.component.scss']
})
export class InformaLaboralComponent implements OnInit{

  jobInformationForm: FormGroup;

  private updateIntervalMinutes = 3;
  private updateIntervalMillis = this.updateIntervalMinutes * 60 * 1000;
  private userActivitySubscription: Subscription | undefined;

  constructor(private tokenService: TokenService, private authService: AuthService,
    private inactivityService: InactivityService, private formBuilder:FormBuilder,
    private loansAppService: LoansApplicationService, private router:Router){

      this.jobInformationForm = formBuilder.group({
        company:['', Validators.required],
        businessCategory:['', Validators.required],
        occupation:['', Validators.required],
        yearsInCompany:['', Validators.required],
        monthlyIncome:['', Validators.required],
        city:['', Validators.required],
        state:['', Validators.required],
        address:['', Validators.required],
        apartment:['', Validators.required],
        zipcode:['', Validators.required],
        phone:['', Validators.required]

      });
    }

    createJobInformation(){
      if(this.jobInformationForm.valid){
        //Obtengo el id del localEstore
        const loanApplicationId = this.tokenService.getLoanApplicationId() || '';
        //Creo objeto
        const jobInfo: JobInformationRequest = {
            loanApplicationId: loanApplicationId,
            company: this.jobInformationForm.get('company')?.value,
            businessCategory: this.jobInformationForm.get('businessCategory')?.value,
            occupation: this.jobInformationForm.get('occupation')?.value,
            yearsInCompany: this.jobInformationForm.get('yearsInCompany')?.value,
            monthlyIncome: this.jobInformationForm.get('monthlyIncome')?.value,
            city: this.jobInformationForm.get('city')?.value,
            state: this.jobInformationForm.get('state')?.value,
            address: this.jobInformationForm.get('address')?.value,
            apartment: this.jobInformationForm.get('apartment')?.value,
            zipcode: this.jobInformationForm.get('zipcode')?.value,
            phone: this.jobInformationForm.get('phone')?.value,
          }

        //Llamo servicio
        this.loansAppService.saveJobInformation(jobInfo).subscribe({
          next: response =>{
            console.log(response);

          },
          error: err =>{

            console.log(err);
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: err,

            })
          },
          complete: () =>{
            console.log('Datos guardados correctamente');
            Swal.fire({
              //position: 'center',
              icon: 'success',
              title: 'Datos guardados!',
              //text: 'OK',
              confirmButtonText: 'OK'


            }).then((result) => {
              if(result.isConfirmed){
                setTimeout(() => {
                  this.router.navigateByUrl('/home/garante');
                },1000);
              }
            })

          },
        });
      }else{
        alert('Error al cargar datos');
        this.jobInformationForm.markAllAsTouched();
      }

    }


  ngOnInit(): void {
    if (this.tokenService.isLoggued()) {
      // Actualizar token cada 3 minutos
      this.userActivitySubscription = interval(this.updateIntervalMillis).subscribe(() => {
        this.updateToken();
      });
      this.inactivityService.startInactivityTimer();
    }
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
