import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeRoutingModule } from './home-routing.module';
import { HomePage } from './pages/home-page/home.page';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { MatIconModule } from '@angular/material/icon';
import { FooterComponent } from './pages/footer/footer.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from 'src/app/interceptors/token.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GeneralMaterialComponent } from './pages/general-material/general-material.component';
import { CalculadoraComponent } from './pages/calculadora/calculadora.component';
import {MatSliderModule} from '@angular/material/slider';
import { GaranteComponent } from './pages/garante/garante.component';
import { InformaLaboralComponent } from './pages/informa-laboral/informa-laboral.component';
import { ConfirmationComponent } from './pages/confirmation/confirmation.component';


@NgModule({
  declarations: [
    HomePage,
    NavbarComponent,
    FooterComponent,
    GeneralMaterialComponent,
    CalculadoraComponent,

    GaranteComponent,
    InformaLaboralComponent,
    ConfirmationComponent,

  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    MatIconModule,
    ReactiveFormsModule,
    FormsModule,
    MatIconModule,
    MatSliderModule

  ],
  exports:[
    NavbarComponent,
    FooterComponent
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi:true}
  ],
})
export class HomeModule { }
