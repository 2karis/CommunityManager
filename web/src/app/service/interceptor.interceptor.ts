import { HttpInterceptorFn } from '@angular/common/http';
import { StorageService } from './storage.service';
import { inject } from '@angular/core';

export const interceptorInterceptor: HttpInterceptorFn = (req, next) => {
  if(StorageService.isUserLoggedIn()){
    const token = StorageService.getToken();
    const reqWithAuth = req.clone({
      headers: req.headers.set('Authorization', 'Bearer '+token),
      
    });
    return next(reqWithAuth);
  }
  return next(req);
};
