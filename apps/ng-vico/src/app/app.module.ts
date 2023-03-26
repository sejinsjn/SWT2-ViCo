import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { NxWelcomeComponent } from './nx-welcome.component';
import { RouterModule } from '@angular/router';

import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonToggleModule } from '@angular/material/button-toggle';

import { RaumuebersichtComponent } from './components/raumuebersicht/raumuebersicht.component';
import { RaumplanEtageComponent } from './components/raumplan-etage/raumplan-etage.component';
import { RaumlisteComponent } from './components/raumliste/raumliste.component';
import { RaumlisteneintragComponent } from './components/raumlisteneintrag/raumlisteneintrag.component';
import { EtagennavigationComponent } from './components/etagennavigation/etagennavigation.component';

@NgModule({
  declarations: [
    AppComponent,
    NxWelcomeComponent,
    RaumuebersichtComponent,
    RaumplanEtageComponent,
    RaumlisteComponent,
    RaumlisteneintragComponent,
    EtagennavigationComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    RouterModule.forRoot([], { initialNavigation: 'enabledBlocking' }),
    MatButtonModule,
    MatProgressSpinnerModule,
    MatDividerModule,
    MatButtonToggleModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
