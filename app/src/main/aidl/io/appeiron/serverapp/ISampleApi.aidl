// ISampleApi.aidl
package io.appeiron.serverapp;

import io.appeiron.serverapp.IResponseCallback;

//Interface for the main api
interface ISampleApi {

   void  respond(IResponseCallback cb);
}