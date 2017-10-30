package com.loujie.util.result;

public abstract class IResultDto {

	public ResultDto call() {
		ResultDto resultDto = new ResultDto();

		String errorData = "";

		try {
			this.run(resultDto, errorData);

		} catch (Exception e) {
		}

		return resultDto;
	}

	public abstract void run(ResultDto resultDto, String errorData);

}
