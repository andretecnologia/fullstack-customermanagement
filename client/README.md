comandos
ng new client --skip-install --skipTests=true --skip-git

git init && git config core.autocrlf true

rm -rf .git

cd client

npm install

npm install @angular/material @angular/cdk

ng g s shared/customer/customer

ng g c customer-list --skipTests=true

ng serve --live-reload=false

ng build --base-href /frontend --deploy-url /frontend/ --prod

style.css
` @import "~@angular/material/prebuilt-themes/indigo-pink.css"; @import 'https://fonts.googleapis.com/icon?family=Material+Icons';

body { margin: 0; font-family: Roboto, sans-serif; }

.mat-toolbar.mat-primary { background: #a50f91; color: #fff; } `

customer-list.component.css
` .mat-list-avatar { width: 80px!important; height: 80px!important; }

.mat-list-item { height: 100px!important; }

.mat-card { font-size: xx-large!important; }

.mat-line { font-size: larger!important; }

.mat-card { box-shadow: 0 2px 2px 0 rgba(255, 255, 255, 1), 0 1px 5px 0 rgba(255, 255, 255, 1), 0 3px 1px -2px rgba(255, 255, 255, 1)!important; } `

customer-list.component.html
` Customer List <mat-list-item *ngFor="let customer of customers"> {{customer.firstName}}

{{customer.firstName}} {{customer.lastName}}
`

customer-list.component.ts
` import { Component, OnInit } from '@angular/core'; import { CustomerService } from '../shared/customer/customer.service'; import { GiphyService } from '../shared/giphy.service';

@Component({ selector: 'app-customer-list', templateUrl: './customer-list.component.html', styleUrls: ['./customer-list.component.css'] }) export class CustomerListComponent implements OnInit {

customers: Array;

constructor(private customerService: CustomerService, private giphyService: GiphyService) { }

ngOnInit() { this.customerService.findAll().subscribe(data => { this.customers = data; for (const customer of this.customers) { this.giphyService.get(customer.firstName).subscribe(url => customer.giphyUrl = url); } }); }

}

`

customer.service.ts
` import { Injectable } from '@angular/core'; import { HttpClient } from '@angular/common/http'; import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'}) export class CustomerService {

constructor(private http: HttpClient) { }

findAll(): Observable { return this.http.get('//localhost:8090/customers'); } } `

app.module.ts
` import { BrowserModule } from '@angular/platform-browser'; import { NgModule } from '@angular/core'; import { HttpClientModule } from '@angular/common/http'; import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; import { MatButtonModule } from '@angular/material/button'; import { MatCardModule } from '@angular/material/card'; import { MatInputModule } from '@angular/material/input'; import { MatListModule } from '@angular/material/list'; import { MatToolbarModule } from '@angular/material/toolbar'; import { AppRoutingModule } from './app-routing.module'; import { AppComponent } from './app.component'; import { CustomerListComponent } from './customer-list/customer-list.component';

@NgModule({ declarations: [ AppComponent, CustomerListComponent ], imports: [ BrowserModule, AppRoutingModule, HttpClientModule, BrowserAnimationsModule, MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule ], providers: [], bootstrap: [AppComponent] }) export class AppModule { }

`

app.component.ts
` import { Component } from '@angular/core';

@Component({ selector: 'app-root', templateUrl: './app.component.html', styleUrls: ['./app.component.css'] }) export class AppComponent { title = 'Customer Management'; }

`

giphy.service.ts
` import { Injectable } from '@angular/core'; import { HttpClient } from '@angular/common/http'; import { map } from 'rxjs/operators';

@Injectable({providedIn: 'root'})

export class GiphyService {

giphyApi = '//api.giphy.com/v1/gifs/search?api_key=9jSUjnX4BtNWXeesDtzyRHiap3fXbHLi&limit=1&q=';

constructor(public http: HttpClient) { }

get(searchTerm) { const apiLink = this.giphyApi + searchTerm; return this.http.get(apiLink).pipe(map((response: any) => { if (response.data.length > 0) { return response.data[0].images.original.url; } else { return 'https://media.giphy.com/media/YaOxRsmrv9IeA/giphy.gif'; } })); } } `

