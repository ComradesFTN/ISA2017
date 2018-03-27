package isa.projekat.controller.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
