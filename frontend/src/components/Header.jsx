import { useState } from "react";
import logo from "/equipe2/logo.png";
import ShoppingCartIcon from "../assets/icons";
import ShoppingCart from "./ShoppingCart";
import { Link } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { User } from "lucide-react";

const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const toggleMenu = () => setIsMenuOpen(prev => !prev);

  return (
    <>
      <header className="bg-primary w-full shadow">
        <div className="container mx-auto flex items-center justify-between px-4 py-4 md:px-6">
          <Link to="/" aria-label="Voltar para a página inicial" className="w-24">
            <img src={logo} alt="Logo da Borcelle Pizzaria" className="rounded-full" />
          </Link>

          <button
            className="text-secondary text-3xl md:hidden focus:outline-none"
            onClick={toggleMenu}
            aria-label={isMenuOpen ? "Fechar menu" : "Abrir menu"}
            aria-expanded={isMenuOpen}
          >
            &#9776;
          </button>

          <nav className="hidden md:flex items-center space-x-6">
            <ul className="flex gap-6 md:gap-20 text-xl text-secondary pr-10">
              <li>
                <Link to="/about" className="hover:brightness-90 transition duration-200">
                  Sobre
                </Link>
              </li>
              <li>
                <Link to="/menu" className="hover:brightness-90 transition duration-200">
                  Menu
                </Link>
              </li>
            </ul>
            <div className="relative">
              <div className="dropdown dropdown-end">
                <button
                  tabIndex={0}
                  className="btn btn-secondary w-52 flex items-center justify-between focus:outline-none"
                  aria-label="Abrir carrinho de compras"
                >
                  <span className="text-primary font-semibold">Veja meu carrinho</span>
                  <div className="indicator">
                    <ShoppingCartIcon className="text-2xl text-primary" aria-hidden="true" />
                    <span className="badge badge-sm badge-info indicator-item">8</span>
                  </div>
                </button>
                <ShoppingCart />
              </div>
            </div>
            <Dialog>
              <DialogTrigger asChild>
                <Button ghost size="icon" className="hover:brightness-90 hidden bg-secondary md:inline-flex" aria-label="Abrir login do usuário">
                  <User className="h-5 w-5" aria-hidden="true" />
                </Button>
              </DialogTrigger>
              <DialogContent className="bg-base-100">
                <DialogHeader>
                  <DialogTitle className="text-neutral">Logar ou Registrar</DialogTitle>
                </DialogHeader>
                <Tabs defaultValue="signin" className="w-full">
                  <TabsList className="grid w-full grid-cols-2">
                    <TabsTrigger value="signin">Logar</TabsTrigger>
                    <TabsTrigger value="signup">Registrar</TabsTrigger>
                  </TabsList>
                  <TabsContent value="signin">
                    <form className="space-y-4">
                      <Input placeholder="Email" type="email" required />
                      <Input placeholder="Senha" type="password" required />
                      <Button className="w-full">Logar</Button>
                    </form>
                  </TabsContent>
                  <TabsContent value="signup">
                    <form className="space-y-4">
                      <Input placeholder="Nome" required />
                      <Input placeholder="Email" type="email" required />
                      <Input placeholder="Senha" type="password" required />
                      <Input placeholder="Confirme a Senha" type="password" required />
                      <Button className="w-full">Registrar</Button>
                    </form>
                  </TabsContent>
                </Tabs>
              </DialogContent>
            </Dialog>
          </nav>
        </div>

        {/* Navbar para mobile */}
        {isMenuOpen && (
          <nav className="md:hidden bg-primary text-secondary text-xl">
            <ul className="flex flex-col items-center gap-4 p-4">
              <li>
                <Link to="/about" className="hover:brightness-90 transition duration-200" onClick={toggleMenu}>
                  Sobre
                </Link>
              </li>
              <li>
                <Link to="/menu" className="hover:brightness-90 transition duration-200" onClick={toggleMenu}>
                  Menu
                </Link>
              </li>
              <li>
                <Link to="/cart" className="hover:brightness-90 transition duration-200" onClick={toggleMenu}>
                  Veja meu carrinho
                </Link>
              </li>
            </ul>
          </nav>
        )}
      </header>

      <section
        className="relative w-full h-[400px] md:h-[500px] bg-cover bg-center"
        style={{ backgroundImage: "url('/equipe2/hero-pizza.png')" }}
      >
        <div className="absolute inset-0 flex flex-col items-center justify-center bg-black bg-opacity-50">
          <h1 className="text-4xl font-bold text-yellow-500">Borcelle Pizza</h1>
          <p className="mt-2 text-xl text-white">Hoje é dia de vale pizza!</p>
          <Link to="/menu" className="mt-4">
            <button className="px-6 py-2 text-lg font-medium text-white bg-red-600 rounded-md hover:bg-red-700 transition duration-200 focus:outline-none">
              Veja as opções
            </button>
          </Link>
        </div>
      </section>
    </>
  );
};

export default Header;
