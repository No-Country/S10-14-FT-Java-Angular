import { NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatIconModule } from '@angular/material/icon';
import { CoreModule } from './core/core.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';








@NgModule({
  declarations: [
    AppComponent,



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    CoreModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatIconModule,
    ReactiveFormsModule,
    SweetAlert2Module.forRoot(),


  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi:true}
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
