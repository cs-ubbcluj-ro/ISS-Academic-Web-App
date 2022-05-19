import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { LoginComponent } from './login/login.component';
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import { TeacherDashboardComponent } from './teacher-dashboard/teacher-dashboard.component';
import { TestingDashboardComponent } from './testing-dashboard/testing-dashboard.component';
import { HomeComponent } from './components/pages/home/home.component';
import { StudentMenuComponent } from './components/menus/student-menu/student-menu.component';
import { ProfilePageComponent } from './components/pages/profile-page/profile-page.component';
import { DocumentsPageComponent } from './components/pages/documents-page/documents-page.component';
import { CurriculumGradesPageComponent } from './components/pages/curriculum-grades-page/curriculum-grades-page.component';
import { ProposedOptionalsPageComponent } from './components/pages/proposed-optionals-page/proposed-optionals-page.component';
import { EnrollComponent } from './components/pages/enroll/enroll.component';

const routes: Routes = [
  {path:'', redirectTo:'login', pathMatch:'full'},
  {path:'login', component:LoginComponent},

  //{path:'student-dashboard', component:StudentDashboardComponent},
  {path:'student-dashboard', component:StudentDashboardComponent, 
    children: [
      {path:'home', component:HomeComponent},
      {path:'profile', component:ProfilePageComponent},
      {path:'documents', component:DocumentsPageComponent},
      {path:'curriculum-grades', component:CurriculumGradesPageComponent},
      {path:'optional-courses', component:ProposedOptionalsPageComponent},
      {path:'enroll', component:EnrollComponent}
    ]},
  

  {path:'teacher-dashboard', component:TeacherDashboardComponent},
  {path:'staff-dashboard', component:AdminDashboardComponent},
  {path:'testing', component: TestingDashboardComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
