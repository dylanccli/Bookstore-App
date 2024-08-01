/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

/**
 *
 * @author Dylan
 */
public class SetState {
   private State state;

   public SetState(){
      state = null;
   }

   public void setState(State state){
      this.state = state;	
   }

   public State getState(){
      return state;
   }
}