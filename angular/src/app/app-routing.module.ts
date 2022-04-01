import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BlogListComponent, BlogAddComponent, BlogEditComponent, BlogViewComponent } from './blog';
import { CalculadoraComponent } from './calculadora/calculadora.component';
import { ContactosAddComponent, ContactosEditComponent, ContactosListComponent, ContactosViewComponent } from './contactos';
import { DemosComponent } from './demos/demos.component';
import { FormularioComponent } from './formulario/formulario.component';
import { LibrosComponent } from './libros';
import { HomeComponent, PageNotFoundComponent } from './main';
import { AuthGuard, InRoleGuard, RegisterUserComponent } from './security';
import { UploadComponent } from './upload/upload.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'inicio', component: HomeComponent },
  { path: 'demos', component: DemosComponent, data: { pageTitle: 'Demos' } },
  { path: 'upload', component: UploadComponent },
  { path: 'formulario', component: FormularioComponent },
  { path: 'chisme/de/hacer/numeros', component: CalculadoraComponent, data: { pageTitle: 'Calculadora' } },
  { path: 'contactos', component: ContactosListComponent, data: { pageTitle: 'Contactos' } },
  { path: 'contactos/add', component: ContactosAddComponent, canActivate: [AuthGuard] },
  { path: 'contactos/:id/edit', component: ContactosEditComponent, canActivate: [AuthGuard] },
  { path: 'contactos/:id', component: ContactosViewComponent },
  { path: 'contactos/:id/:kk', component: ContactosViewComponent },
  { path: 'alisha/passion', redirectTo: '/contactos/9' },
  {
    path: 'libros', children: [
      { path: '', component: LibrosComponent },
      { path: 'add', component: LibrosComponent },
      { path: ':id/edit', component: LibrosComponent },
      { path: ':id', component: LibrosComponent },
      { path: ':id/:kk', component: LibrosComponent },
    ], canActivateChild: [InRoleGuard], data: { roles: ['Administradores', 'Empleados'] }
  },
  {
    path: 'blog', children: [
      { path: '', component: BlogListComponent },
      { path: 'add', component: BlogAddComponent },
      { path: ':id/edit', component: BlogEditComponent },
      { path: ':id', component: BlogViewComponent },
      { path: ':id/:kk', component: BlogViewComponent },
    ]
  },
  {
    path: 'config', loadChildren: () => import('./config/config.module').then(mod => mod.ConfigModule),
    canLoad: [InRoleGuard], data: { roles: ['Administradores'] }
  },
  { path: 'registro', component: RegisterUserComponent },

  // { path: 'blog', loadChildren: () => import('./blog').then(mod => mod.BlogModule)},
  { path: '404.html', component: PageNotFoundComponent },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
