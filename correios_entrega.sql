-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 06-Mar-2021 às 00:23
-- Versão do servidor: 10.1.32-MariaDB
-- PHP Version: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `correios_entrega`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cargo`
--

CREATE TABLE `cargo` (
  `codCargo` int(11) NOT NULL,
  `nomeCargo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cargo`
--

INSERT INTO `cargo` (`codCargo`, `nomeCargo`) VALUES
(1, 'Administrador'),
(2, 'Gerente'),
(3, 'Motorista');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `dataNascimento` date NOT NULL,
  `sexo` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`cpf`, `nome`, `dataNascimento`, `sexo`) VALUES
('031.842.320-00', 'Lucas Pacheco', '1997-10-12', 'F'),
('138.435.466-22', 'Marcella', '1998-10-11', 'F'),
('405.650.120-37', 'Milena', '1998-10-11', 'F'),
('476.401.650-83', 'Gustavo', '1997-10-11', 'M'),
('843.870.180-97', 'Mara', '1998-10-11', 'F');

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `codEndereco` int(11) NOT NULL,
  `pais` varchar(30) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `rua` varchar(30) NOT NULL,
  `numero` int(11) NOT NULL,
  `complemento` varchar(30) DEFAULT NULL,
  `bairro` varchar(30) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `uf` varchar(15) NOT NULL,
  `identificacao` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`codEndereco`, `pais`, `cep`, `rua`, `numero`, `complemento`, `bairro`, `cidade`, `uf`, `identificacao`) VALUES
(1, 'Brasil', '36773546', 'Tiradentes', 432, NULL, 'Miguel', 'Cataguases', 'MG', ''),
(2, 'Bolívia', '33333-333', 'R', 4, 'C', 'B', 'C', 'Amazonas (AM)', 'Casa'),
(3, 'Argentina', '55555-555', 'R', 2, 'C', 'B', 'Ci', 'Acre (AC)', 'Casa'),
(4, 'Chile', '44444-444', 'R', 22, 'C', 'B', 'C', 'Amazonas (AM)', 'Trabalho'),
(5, 'Argentina', '44444-444', 'R', 4, 'C', 'B', 'C', 'Acre (AC)', 'Trabalho');

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco_cliente`
--

CREATE TABLE `endereco_cliente` (
  `fk_Endereco_codEndereco` int(11) DEFAULT NULL,
  `fk_Cliente_cpf` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `endereco_cliente`
--

INSERT INTO `endereco_cliente` (`fk_Endereco_codEndereco`, `fk_Cliente_cpf`) VALUES
(2, '843.870.180-97'),
(3, '031.842.320-00'),
(5, '031.842.320-00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `dataNascimento` date NOT NULL,
  `sexo` char(1) NOT NULL,
  `fk_Cargo_codCargo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`cpf`, `nome`, `dataNascimento`, `sexo`, `fk_Cargo_codCargo`) VALUES
('035.325.330-81', 'Root', '1993-02-01', 'F', 1),
('117.119.840-00', 'Alysson', '1997-10-11', 'M', 3),
('138.435.466-22', 'Mirella', '1998-10-11', 'F', NULL),
('491.564.360-33', 'Ana', '1995-10-11', 'F', 3),
('555.111.430-90', 'Mel', '1998-10-11', 'F', 1),
('728.377.930-27', 'Lucas', '1997-10-12', 'M', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `localizacao`
--

CREATE TABLE `localizacao` (
  `codLocalizacao` int(11) NOT NULL,
  `horario` time NOT NULL,
  `data` date NOT NULL,
  `descricao` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `localizacao_postagem`
--

CREATE TABLE `localizacao_postagem` (
  `fk_Localizacao_codLocalizacao` int(11) DEFAULT NULL,
  `fk_Postagem_codPostagem` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `login`
--

CREATE TABLE `login` (
  `codLogin` int(11) NOT NULL,
  `senha` varchar(12) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `login`
--

INSERT INTO `login` (`codLogin`, `senha`, `email`) VALUES
(2, '123', 'ma@gmail.com'),
(3, 'root', 'root@'),
(22, '1234', 'mi@u'),
(23, '1234', 'gustavo@'),
(24, '1234', 'mara@'),
(25, '1234', 'lucas@');

-- --------------------------------------------------------

--
-- Estrutura da tabela `login_cliente`
--

CREATE TABLE `login_cliente` (
  `fk_Cliente_cpf` varchar(14) DEFAULT NULL,
  `fk_Login_codLogin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `login_cliente`
--

INSERT INTO `login_cliente` (`fk_Cliente_cpf`, `fk_Login_codLogin`) VALUES
('138.435.466-22', 2),
('405.650.120-37', 22),
('476.401.650-83', 23),
('843.870.180-97', 24),
('031.842.320-00', 25);

-- --------------------------------------------------------

--
-- Estrutura da tabela `login_funcionario`
--

CREATE TABLE `login_funcionario` (
  `fk_Funcionario_cpf` varchar(14) DEFAULT NULL,
  `fk_Login_codLogin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `login_funcionario`
--

INSERT INTO `login_funcionario` (`fk_Funcionario_cpf`, `fk_Login_codLogin`) VALUES
(NULL, NULL),
('035.325.330-81', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `objeto`
--

CREATE TABLE `objeto` (
  `codObejto` int(11) NOT NULL,
  `peso` float NOT NULL,
  `profundidade` float NOT NULL,
  `altura` float NOT NULL,
  `fk_Postagem_codPostagem` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `postagem`
--

CREATE TABLE `postagem` (
  `codPostagem` int(11) NOT NULL,
  `status` varchar(30) NOT NULL,
  `valor` float NOT NULL,
  `fk_Cliente_cpf` varchar(14) DEFAULT NULL,
  `fk_Servico_codSServico` int(11) DEFAULT NULL,
  `fk_Endereco_codEndereco` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `servico`
--

CREATE TABLE `servico` (
  `codSServico` int(11) NOT NULL,
  `tempoEntrega` float NOT NULL,
  `maxPeso` float NOT NULL,
  `maxCentimetros` float NOT NULL,
  `nomeServico` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`codCargo`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cpf`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`codEndereco`);

--
-- Indexes for table `endereco_cliente`
--
ALTER TABLE `endereco_cliente`
  ADD KEY `FK_Endereco_Cliente_1` (`fk_Endereco_codEndereco`),
  ADD KEY `FK_Endereco_Cliente_2` (`fk_Cliente_cpf`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`cpf`),
  ADD KEY `FK_Funcionario_2` (`fk_Cargo_codCargo`);

--
-- Indexes for table `localizacao`
--
ALTER TABLE `localizacao`
  ADD PRIMARY KEY (`codLocalizacao`);

--
-- Indexes for table `localizacao_postagem`
--
ALTER TABLE `localizacao_postagem`
  ADD KEY `FK_Localizacao_Postagem_1` (`fk_Localizacao_codLocalizacao`),
  ADD KEY `FK_Localizacao_Postagem_2` (`fk_Postagem_codPostagem`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`codLogin`);

--
-- Indexes for table `login_cliente`
--
ALTER TABLE `login_cliente`
  ADD KEY `FK_Login_Cliente_1` (`fk_Cliente_cpf`),
  ADD KEY `FK_Login_Cliente_2` (`fk_Login_codLogin`);

--
-- Indexes for table `login_funcionario`
--
ALTER TABLE `login_funcionario`
  ADD KEY `FK_Login_Funcionario_1` (`fk_Funcionario_cpf`),
  ADD KEY `FK_Login_Funcionario_2` (`fk_Login_codLogin`);

--
-- Indexes for table `objeto`
--
ALTER TABLE `objeto`
  ADD PRIMARY KEY (`codObejto`),
  ADD KEY `FK_Objeto_2` (`fk_Postagem_codPostagem`);

--
-- Indexes for table `postagem`
--
ALTER TABLE `postagem`
  ADD PRIMARY KEY (`codPostagem`),
  ADD KEY `FK_Postagem_2` (`fk_Cliente_cpf`),
  ADD KEY `FK_Postagem_3` (`fk_Servico_codSServico`),
  ADD KEY `FK_Postagem_4` (`fk_Endereco_codEndereco`);

--
-- Indexes for table `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`codSServico`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cargo`
--
ALTER TABLE `cargo`
  MODIFY `codCargo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `codEndereco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `localizacao`
--
ALTER TABLE `localizacao`
  MODIFY `codLocalizacao` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `codLogin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `objeto`
--
ALTER TABLE `objeto`
  MODIFY `codObejto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `postagem`
--
ALTER TABLE `postagem`
  MODIFY `codPostagem` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `servico`
--
ALTER TABLE `servico`
  MODIFY `codSServico` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `endereco_cliente`
--
ALTER TABLE `endereco_cliente`
  ADD CONSTRAINT `FK_Endereco_Cliente_1` FOREIGN KEY (`fk_Endereco_codEndereco`) REFERENCES `endereco` (`codEndereco`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_Endereco_Cliente_2` FOREIGN KEY (`fk_Cliente_cpf`) REFERENCES `cliente` (`cpf`) ON DELETE SET NULL;

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `FK_Funcionario_2` FOREIGN KEY (`fk_Cargo_codCargo`) REFERENCES `cargo` (`codCargo`) ON DELETE SET NULL;

--
-- Limitadores para a tabela `localizacao_postagem`
--
ALTER TABLE `localizacao_postagem`
  ADD CONSTRAINT `FK_Localizacao_Postagem_1` FOREIGN KEY (`fk_Localizacao_codLocalizacao`) REFERENCES `localizacao` (`codLocalizacao`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_Localizacao_Postagem_2` FOREIGN KEY (`fk_Postagem_codPostagem`) REFERENCES `postagem` (`codPostagem`) ON DELETE SET NULL;

--
-- Limitadores para a tabela `login_cliente`
--
ALTER TABLE `login_cliente`
  ADD CONSTRAINT `FK_Login_Cliente_1` FOREIGN KEY (`fk_Cliente_cpf`) REFERENCES `cliente` (`cpf`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_Login_Cliente_2` FOREIGN KEY (`fk_Login_codLogin`) REFERENCES `login` (`codLogin`) ON DELETE SET NULL;

--
-- Limitadores para a tabela `login_funcionario`
--
ALTER TABLE `login_funcionario`
  ADD CONSTRAINT `FK_Login_Funcionario_1` FOREIGN KEY (`fk_Funcionario_cpf`) REFERENCES `funcionario` (`cpf`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_Login_Funcionario_2` FOREIGN KEY (`fk_Login_codLogin`) REFERENCES `login` (`codLogin`) ON DELETE SET NULL;

--
-- Limitadores para a tabela `objeto`
--
ALTER TABLE `objeto`
  ADD CONSTRAINT `FK_Objeto_2` FOREIGN KEY (`fk_Postagem_codPostagem`) REFERENCES `postagem` (`codPostagem`) ON DELETE SET NULL;

--
-- Limitadores para a tabela `postagem`
--
ALTER TABLE `postagem`
  ADD CONSTRAINT `FK_Postagem_2` FOREIGN KEY (`fk_Cliente_cpf`) REFERENCES `cliente` (`cpf`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_Postagem_3` FOREIGN KEY (`fk_Servico_codSServico`) REFERENCES `servico` (`codSServico`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_Postagem_4` FOREIGN KEY (`fk_Endereco_codEndereco`) REFERENCES `endereco` (`codEndereco`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
