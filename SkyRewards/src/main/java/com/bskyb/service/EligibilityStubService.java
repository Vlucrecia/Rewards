package com.bskyb.service;
/**
 * Stub for Eligibility Service
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bskyb.util.EligibilityEnum;

@Path("/eligibility")
public class EligibilityStubService {
	@GET
	@Path("/{param}")
	@Produces("application/json")
	public EligibilityEnum getMsg(@PathParam("param") String accountNo) {

		return EligibilityEnum.CUSTOMER_ELIGIBLE;

	}

}
