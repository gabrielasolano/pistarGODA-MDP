package br.unb.cic.goda.rtgoretoprism.generator.analiser;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.unb.cic.goda.exception.ResponseException;

public class SintaticAnaliser {

	private static final Logger LOGGER = Logger.getLogger(SintaticAnaliser.class.getName());

	public static String recoverLogsError() {
		String dir = "/sintaticAnaliser/";

		try {

			StringBuilder command = new StringBuilder().append("./").append(dir)
					.append("goal-model-syntactic-analyzer-linux");

			Process proc = Runtime.getRuntime().exec(command.toString());
			LOGGER.info(proc.getInputStream().toString());
			LOGGER.info(proc.getOutputStream().toString());

			List<String> lines = Files.readAllLines(Paths.get(dir), Charset.forName("UTF-8"));

			if (lines.size() - 1 < 0) {
				throw new ResponseException("Fail to recover logs error.");
			} else {
				return lines.get(lines.size() - 1);

			}
		} catch (Exception error) {
			LOGGER.log(Level.SEVERE, error.getMessage());
			throw new ResponseException(error);

		}

	}
}