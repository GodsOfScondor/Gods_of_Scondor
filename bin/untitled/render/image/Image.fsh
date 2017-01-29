#version 400 core

in vec2 tex_coords;

out vec4 out_color;

uniform sampler2D img;

void main(void){
	vec4 color = texture(img,tex_coords);
	out_color = vec4(color.xyz, min(1.0, color.a));
}