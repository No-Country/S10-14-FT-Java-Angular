import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription, interval } from 'rxjs';
import { GuarantorRequest } from 'src/app/models/guarantor-request';
import { AuthService } from 'src/app/services/auth.service';
import { InactivityService } from 'src/app/services/inactivity.service';
import { LoansApplicationService } from 'src/app/services/loans-application.service';
import { TokenService } from 'src/app/services/token.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-garante',
  templateUrl: './garante.component.html',
  styleUrls: ['./garante.component.scss']
})
export class GaranteComponent implements OnInit{
  guarantorForm: FormGroup;

  private updateIntervalMinutes = 3;
  private updateIntervalMillis = this.updateIntervalMinutes * 60 * 1000;
  private userActivitySubscription: Subscription | undefined;

  constructor(private tokenService: TokenService, private authService: AuthService,
    private inactivityService: InactivityService, private formBuilder:FormBuilder,
    private loanAppService:LoansApplicationService, private router:Router){
      this.guarantorForm = formBuilder.group({
        name: ['', Validators.required],
        lastname:['', Validators.required],
        identificationType:['DNI', Validators.required],
        identification:['', Validators.required],
        city:['', Validators.required],
        state:['', Validators.required],
        address:['', Validators.required],
        apartment:['', Validators.required],
        zipcode:['', Validators.required],
        phone:['', Validators.required],
        company:['', Validators.required],
        businessCategory:['', Validators.required],
        occupation:['', Validators.required],
        timeInCompany:['', Validators.required],
        monthlyIncome:['', Validators.required],
        companyCity:['', Validators.required],
        companyState:['', Validators.required],
        companyAddress:['', Validators.required],
        companyApartment:['', Validators.required],
        companyZipcode:['', Validators.required],
        companyPhone:['', Validators.required]
      })
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

  createGuarantor(){
    if(this.guarantorForm.valid){
      const loanApplicationId = this.tokenService.getLoanApplicationId() || '';
      const selectedIdentificationType = this.guarantorForm.get('identificationType')?.value;

      const guarantorRequest: GuarantorRequest = {
        loanApplicationId: loanApplicationId,
        name: this.guarantorForm.get('name')?.value,
        lastname: this.guarantorForm.get('lastname')?.value,
        identificationType: selectedIdentificationType,
        identification: this.guarantorForm.get('identification')?.value,
        city: this.guarantorForm.get('city')?.value,
        state: this.guarantorForm.get('state')?.value,
        address: this.guarantorForm.get('address')?.value,
        apartment: this.guarantorForm.get('apartment')?.value,
        zipcode: this.guarantorForm.get('zipcode')?.value,
        phone: this.guarantorForm.get('phone')?.value,
        company: this.guarantorForm.get('company')?.value,
        businessCategory: this.guarantorForm.get('businessCategory')?.value,
        occupation: this.guarantorForm.get('occupation')?.value,
        timeInCompany: this.guarantorForm.get('timeInCompany')?.value,
        monthlyIncome: this.guarantorForm.get('monthlyIncome')?.value,
        companyCity: this.guarantorForm.get('companyCity')?.value,
        companyState: this.guarantorForm.get('companyState')?.value,
        companyAddress: this.guarantorForm.get('companyAddress')?.value,
        companyApartment: this.guarantorForm.get('companyApartment')?.value,
        companyZipcode: this.guarantorForm.get('companyZipcode')?.value,
        companyPhone: this.guarantorForm.get('companyPhone')?.value
      }

      //Llamo servicio
      this.loanAppService.saveGuarantor(guarantorRequest).subscribe({
        next: response =>{
          console.log(response);
          //alert('Datos guardados');
        },
        error: err => {
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
            // text: 'OK',
            confirmButtonText: 'OK'


          }).then((result) => {
            if(result.isConfirmed){
              setTimeout(() => {
                this.router.navigateByUrl('/home/confirm');
              },1000);
            }
          })
            // Swal.fire({
            //   //position: 'center',
            //   icon: 'success',
            //   title: 'Gracias por elegirnos!',
            //   html: '<div style="font-size: 18px; color: #333;">Tu solicitud se ha cargado exitosamente y se encuentra en revisión. Nos comunicaremos contigo a la brevedad.</div>',
            //   confirmButtonText: 'OK'


            // }).then((result) => {
            //   if(result.isConfirmed){
            //     setTimeout(() => {
            //       this.router.navigateByUrl('/home/confirm');
            //     },1000);
            //   }
            // })
        }
      })
    }else{
      alert('Error al cargar datos');
      this.guarantorForm.markAllAsTouched();
    }

  }
}
